package service;

import java.io.IOException;
import io.jooby.Jooby;
import io.jooby.OpenAPIModule;
import io.jooby.ServerOptions;
import io.jooby.gson.GsonModule;

public class SalesService extends Jooby {

	public SalesService() {

		install(new GsonModule());
		install(new OpenAPIModule());

		assets("/openapi.json", "sales.json");
		assets("/openapi.yaml", "sales.yaml");

		// mount your resource classes here
		/*
		mount(...);
		mount(...);
		 */

		get("/", ctx -> ctx.sendRedirect("/swagger"));
	}

	public static void main(String[] args) throws IOException {
		System.out.println("\n\n****** Sales Service ******\n\n");
		new SalesService()
			 .setServerOptions(new ServerOptions().setPort(8081))
			 .start();
	}

}
