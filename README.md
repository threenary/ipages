# Welcome!

Thank you for taking your time to implement a little coding challenge for us. Spending 4-5 hours on implementing the requirement will help us get a feeling of how you code and approach new requirements. 

# Requirement

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

## Submit Your Work
Please create a zip file of your solution and send it back to [Serena](mailto:s.tansil@epages.com). We will look at it and further discuss your solution together.
