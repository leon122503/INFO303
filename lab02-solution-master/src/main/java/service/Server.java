package service;

import dao.CatalogueDAO;
import io.jooby.Jooby;
import io.jooby.OpenAPIModule;
import io.jooby.gson.GsonModule;
import io.jooby.handler.Cors;
import io.jooby.handler.CorsHandler;
import resource.CatalogueResource;
import resource.ProductResource;

public class Server extends Jooby {

	public Server() {
		CatalogueDAO dao = new CatalogueDAO();

		// add support for JSON
		install(new GsonModule());

		use(new CorsHandler(new Cors().setMethods("GET", "POST", "PUT", "DELETE")));

		mount(new CatalogueResource(dao));
		mount(new ProductResource(dao));

		install(new OpenAPIModule());

		// provide our OAS specification to the swagger UI
		assets("/openapi.json", "catalogue.json");
		assets("/openapi.yaml", "catalogue.yaml");

		// redirect requests to / to /swagger
		get("/", ctx -> ctx.sendRedirect("/swagger"));
	}

	public static void main(String[] args) {
		new Server().start();
	}

}
