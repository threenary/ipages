#iPages

## Requirement

Our microservice architecture has a service to create and manage products. We now would like to add another microservice that offers search and filter capabilities for products. To quickly provide a working endpoint to our frontend team, we agreed on the following details.

## Read Data from Persistence Store

The service should connect to some persistence store, where it can read products with the following structure:

```JSON
{
	"id": 1,
	"name": "Product A",
	"price": 7.99,
	"brand": "Brand A",
	"onSale": true
}
```

Expected Response
- 
Our Frontend Team expects a response matching the following requirements

- All products are returned
- Products are grouped by `brand`, sorted alphabetically
- Property `brand` should be omitted on products
- Products inside a `brand` should be sorted ascending by `price`
- Property `onSale` should be converted to a property `event` of type String with the value "ON SALE"

Example:

```JSON
{
	"Brand A" : [{
		"id": 1,
		"name": "Product A",
		"price": 7.99,
		"event": "ON SALE"
	},
	{
		"id": 2,
		"name": "Product B",
		"price": 12.99
	}],
	"Brand B" : [{
		"id": 3,
		"name": "Product C",
		"price": 10.99
	},
	{
		"id": 4,
		"name": "Product D",
		"price": 14.99
	}]
}
```

# Technical Requirement  

We expect:  
 - a JVM based solution using JDK 8 or higher
 - that is cloud ready
 - that can be build and executed on any environment ( e.g. local, jenkins )
 - with tests verifying the given requirements 
 - and  a versioned code base

## Freedom of Tools and Technology

Microservice architecture give us the freedom to pick the tools best suited for our skills and requirements.
To quickly get started you can use this https://start.spring.io based skeleton. 

However **we suggest you to use the tools and technologies you are comfortable with**. The solution won't be better just because you used the skeleton. 

## Freedom to Extend
Feel free to come up with more ideas on how to improve the service from technical or business perspective, if there is still time left.

# Resolution Notes

## Technical Approach
Benefited from the usage of __Spring Boot__ that provided the core infrastructure for a cloud ready service.
With the usage of JPA and Hibernate I achieved the querying facilities, using a __H2__ embedded storage for it's simplicity.
Provided __Swagger UI__ for easy use of the REST API for documentation purposes and it's easy to use features.


## Solution Specification

Decided to go for two approaches, which I called In Memory Approach and By JPA Approach.
 
__In Memory__ since the requirement is to grab all the existing products, I decided to filter an grouping them directly
once they are loaded in memory, using the benefits of the Stream API

__By JPA Specification__ came after since I wanted to test the performance, at least by curiosity, and seemed also
a possible approach, using the Specification API provided by JPA, this is something I had never used before and wanted 
to try it out.

Results (without a comprehensive benchmarking), seems faster with the first approach, although this should be tested with a
higher amount of Products.

## Additional considerations

- Default Port ```8090```
- H2 console ```http://localhost:8090/h2-console``` 
- SwaggerUI ```http://localhost:8090/swagger-ui.html```

Defaults can be changed in /src/main/resources/application.yml

## Execution

Please take into account this is not a self executable jar, although it benefits from __Gradlew__ Application plugin, 
thus there is a script that can be used to run the application:

```./run.sh```


 
 