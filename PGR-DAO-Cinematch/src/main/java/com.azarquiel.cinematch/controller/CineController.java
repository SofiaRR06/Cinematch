package controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.CineService;
import model.Pelicula;
import model.UsuarioPelicula;

	

@WebServlet("/cine")
public class CineController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

    private final CineService calcular = new CineService();
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CineController() {
        super();
        // TODO Auto-generated constructor stub
    }
        
        /*Recoge los párametros de la URL y los copia a los atributos, después muestra el JSP*/
      protected void doGet(HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) throws ServletException, IOException {
    	  
    	  request.setCharacterEncoding("UTF-8");


    	  CineService service = new CineService();

    	  String op = request.getParameter("op");
    	    if (op == null) op = "HOME"; 

    	    // Siempre cargamos la lista de géneros para el formulario
    	    List<String> generos = List.of("Ciencia Ficción", "Animación", "Acción", "Drama", "Triller", "Comedia", "Musical");
    	    request.setAttribute("generos", generos);
    	    
    	    ArrayList<Pelicula> peliculas = service.listadoPeliculas();
    	    request.setAttribute("peliculas", peliculas);

    	    // Recuperamos mensajes flash de la sesión
    	    Object flash = request.getSession().getAttribute("flash");
    	    if (flash != null) {
    	        request.setAttribute("flash", flash);
    	        request.getSession().removeAttribute("flash");
    	    }

    	    // Dependiendo de la operación, cargamos los datos necesarios
    	    switch (op) {
    	        case "HOME":
    	            // Últimas valoraciones del usuario
    	            Object ultimas = request.getSession().getAttribute("ultimas");
    	            if (ultimas != null) {
    	                request.setAttribute("ultimas", ultimas);
    	                request.getSession().removeAttribute("ultimas");
    	            }

    	            // Recomendación de película 
    	            Object recomendacion = request.getSession().getAttribute("recomendacion");
    	            if (recomendacion != null) {
    	                request.setAttribute("recomendacion", recomendacion);
    	                request.getSession().removeAttribute("recomendacion");
    	            }

    	            // Valores actuales del formulario
    	            Object aliasActual = request.getSession().getAttribute("aliasActual");
    	            Object generoActual = request.getSession().getAttribute("generoActual");
    	            Object valoracionActual = request.getSession().getAttribute("valoracionActual");

    	            if (aliasActual != null) {
    	                request.setAttribute("aliasActual", aliasActual);
    	                request.getSession().removeAttribute("aliasActual");
    	            }
    	            if (generoActual != null) {
    	                request.setAttribute("generoActual", generoActual);
    	                request.getSession().removeAttribute("generoActual");
    	            }
    	            if (valoracionActual != null) {
    	                request.setAttribute("valoracionActual", valoracionActual);
    	                request.getSession().removeAttribute("valoracionActual");
    	            }

    	            request.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);
    	            break;

    	        case "TOP_PELICULAS":
    	            List<Pelicula> top = service.topPeliculas(10); 
    	            request.setAttribute("topPeliculas", top);
    	            request.getRequestDispatcher("/WEB-INF/views/top.jsp").forward(request, response);
    	            break;

    	        case "CONSULTAR_HISTORIAL":
    	            Object historial = request.getSession().getAttribute("historial");
    	            if (historial != null) {
    	                request.setAttribute("historial", historial);
    	                request.getSession().removeAttribute("historial");
    	            }
    	            request.getRequestDispatcher("/WEB-INF/views/historial.jsp").forward(request, response);
    	            break;

    	        default:
    	            response.sendRedirect("cine?op=HOME");
    	            break;
    	    }
           }
      
      protected void doPost(HttpServletRequest request, HttpServletResponse response)
    	    throws ServletException, IOException {
    	  
    	  request.setCharacterEncoding("UTF-8");


    	    String operacion = request.getParameter("operacion");
    	    String alias = request.getParameter("alias");
    	    String genero = request.getParameter("genero");
    	    String valoracionStr = request.getParameter("valoracion");
    	    String peliculaIdStr = request.getParameter("pelicula");

    	    // Guardamos algunos datos en sesión para mantenerlos en el JSP
    	    request.getSession().setAttribute("aliasActual", alias);
    	    request.getSession().setAttribute("generoActual", genero);
    	    request.getSession().setAttribute("valoracionActual", valoracionStr);

    	    CineService service = new CineService();

    	    switch (operacion) {

    	        case "REGISTRAR":
    	            try {
    	            	int peliculaId = Integer.parseInt(peliculaIdStr);
    	                double valoracion = Double.parseDouble(valoracionStr);
    	                
    	                UsuarioPelicula up = new UsuarioPelicula();
    	                up.setAlias(alias);
    	                up.setValoracion(valoracion);
    	                up.setFechaRegistro(LocalDateTime.now());
    	                up.setIdPelicula(peliculaId);
    	                
    	                
    	                service.registrarPreferencia(up);

    	                request.getSession().setAttribute("flash", "Preferencia registrada correctamente.");

    	            } catch (Exception e) {
    	                e.printStackTrace();
    	                request.getSession().setAttribute("flash", "Error al registrar la preferencia.");
    	            }

    	            response.sendRedirect("cine?op=HOME");
    	            break;

    	        case "RECOMENDAR":
    	        	try {
    	                if (alias == null || alias.isEmpty()) {
    	                    request.getSession().setAttribute("flash", "Debes indicar un alias.");
    	                    response.sendRedirect("cine?op=HOME");
    	                    return;
    	                }

    	                if (genero == null || genero.isEmpty()) {
    	                    request.getSession().setAttribute("flash", "Debes seleccionar un género.");
    	                    response.sendRedirect("cine?op=HOME");
    	                    return;
    	                }
    	                System.out.println("Género recibido: [" + genero + "]");

    	                // Llamada al servicio para obtener la recomendación
    	                Optional<Pelicula> peliculaOpt = service.recomendarPelicula(genero);

    	                if (peliculaOpt.isPresent()) {
    	                    Pelicula recomendacion = peliculaOpt.get(); // Extraemos la película real
    	                    request.getSession().setAttribute("recomendacion", recomendacion); // Guardamos en sesión para PRG
    	                    request.getSession().setAttribute("aliasActual", alias);
    	                    request.getSession().setAttribute("generoActual", genero);

    	                    response.sendRedirect("cine?op=HOME"); // Redirigimos al home para mostrar la recomendación
    	                } else {
    	                    request.getSession().setAttribute("flash", "No se encontró ninguna película para el género seleccionado.");
    	                    response.sendRedirect("cine?op=HOME");
    	                }

    	            } catch (Exception e) {
    	                e.printStackTrace();
    	                request.getSession().setAttribute("flash", "Error al generar la recomendación.");
    	                response.sendRedirect("cine?op=HOME");
    	            }
    	            break;

    	        case "CONSULTAR_HISTORIAL":
    	            List<UsuarioPelicula> historial = service.consultarHistorial(alias);
    	            request.getSession().setAttribute("historial", historial);
    	            response.sendRedirect("cine?op=CONSULTAR_HISTORIAL");
    	            break;

    	        case "BORRAR_HISTORIAL":
    	            service.borrarHistorial(alias);
    	            request.getSession().setAttribute("flash", "Historial borrado correctamente.");
    	            response.sendRedirect("cine?op=HOME");
    	            break;

    	        case "TOP_PELICULAS":
    	            response.sendRedirect("cine?op=TOP_PELICULAS");
    	            break;

    	        default:
    	            response.sendRedirect("cine?op=HOME");
    	            break;
    	    }
    	}

    }
    
    


    
   