# A quick and practical example of Hexagonal Architecture in Java
This java code example illustrates how we build the Ports & Adapters in real world and what benefits this architectural design brings to us.

The component coreapp is the application core and includes the domain entities and business rules. The component cli-ui-adapter and repository-adapter are adapters . The former adapter drives application core and the latter one serves the application core.

