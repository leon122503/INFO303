package graphql;

import graphql.schema.idl.RuntimeWiring;

public class AccountWiring {

	public RuntimeWiring getWiring() {
		return RuntimeWiring
			.newRuntimeWiring()

			/* add wiring here */

			.build();
	}

}
