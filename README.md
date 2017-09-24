#URLShortener Service
- [Intro](#intro)
- [Architectural Layers](#architectural-layers)

## Intro
The URL Shortener Service is a microservice that allows creation of shorter urls.  

## Microservice

A microservice is a mini-application that has its own architecture, including its own database and schema. The database and schema are not shared with any other service - this helps ensure loose coupling.

Benefits
1. Rreduces complexity by decompsing the application into a manageable set of services. Compared to a monolithic application, each service is faster to develop, easier to understand and maintain.
2. Each service is developed independently by separate teams and developed using th most current technologies - the team is not bound to potentially obsolete technology that is used by a monolithic application or other services.
3. Each microservice is deployed independently - enabling continuous deployment.
4. Each microservice is scaled independently.

See [Microservices: From Design to Deployment](.readme/Microservices_Designing_Deploying.pdf)

## Architectural Layers

The architectural layers of the Grant Service are based on the Domain Driven Design architectural layers. See [Domain Driven Design Quickly](.readme/DomainDrivenDesignQuicklyOnline.pdf)

- Application layer exposes the this Service to via REST API. No authentication is required.
- Domain layer provides domain specific business logic.
- Infrastructure layer provides the domain specific implementation needed to:
	- Persist and retrieve data from a domain specific repository
- Domain model provide domain specific value objects.

## Directory Structure
The directory structure of the Grant Service makes the architectural layers explicit.

![directory structure](.readme/directory-structure.PNG)

## Technical Decision Wiki
Various decision and technical details are document in the [Grant Service GitHub wiki](https://github.intuit.com/iam/grant-service/wiki).

## API Documentation
Available via Swagger
On you local machine: http://localhost:8080/swagger-ui.html

