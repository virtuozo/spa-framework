Virtuoze
==========

Virtuoze is a spa framework written in Java. It is based on design patterns and it is built on top of gwt. Maturity and simplicity is all that you can expect from it.

3.0.0 Change Log
==========

- Domain Driven Design approach to package names
- Reviewed Fluent API
	- Properties Binding Listeners
	- Components behavior reviewed
- UI API 
	- Widget unified all the former api classes (Component, Node, Composite, Selector, EventSource)
	- UI Components does not expose GWT Widget class anymore. Now, all the UI API is based on UIWidget interface
- UI Components Deprecation
	- InputGroup
	- InputPanel
- UI New Components
	- BrowserStorage
	- AsynchBrowserStorage for Rest API
