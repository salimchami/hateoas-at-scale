# hateoas-at-scale

[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE.md)
![Last Commit](https://img.shields.io/github/last-commit/salimchami/hateoas-at-scale)
![Number of Contributors](https://img.shields.io/github/contributors/salimchami/hateoas-at-scale)
![Main Language](https://img.shields.io/github/languages/top/salimchami/hateoas-at-scale)

[![main-build](https://github.com/salimchami/hateoas-at-scale/actions/workflows/build.yml/badge.svg)](https://github.com/salimchami/hateoas-at-scale/actions/workflows/build.yml)
[![PR-build](https://github.com/salimchami/hateoas-at-scale/actions/workflows/pull-request.yml/badge.svg)](https://github.com/salimchami/hateoas-at-scale/actions/workflows/pull-request.yml)

**A demonstration of HATEOAS implementation at scale with [Spring Boot](https://spring.io/projects/spring-boot), [Consul](https://developer.hashicorp.com/consul) and [Nginx](https://nginx.org/).**

This project aims to showcase an approach to implement HATEOAS (Hypermedia as the Engine of Application State) in RESTful applications designed for scalability. It provides a concrete example of an architecture and techniques for managing hypermedia links efficiently in distributed systems.

## Table of Contents

* [Key Features](#key-features)
* [Workflows](#workflows)
  * [Functional workflow](#functional-workflow) 
  * [Hateoas Workflow](#hateoas-workflow) 
* [Quick Start](#quick-start)
  * [Prerequisites](#prerequisites)
  * [Installation](#installation)
  * [Execution](#execution)
* [Usage](#usage)
  * [Request and response examples](#request-and-response-examples)
* [Contributions](#contributions)
* [License](#license)
* [Contact](#contact)

## Key Features

* **HATEOAS Implementation:** Utilizes Spring HATEOAS features to dynamically generate hypermedia links in API responses.
* **Scalability-focused Design:** Explores strategies for managing HATEOAS links in a microservices or distributed system context.
* **Concrete Example:** Provides an example of a simple API (e.g., cart management) integrating HATEOAS.
* **Spring Boot Usage:** Simplifies the setup and execution of the applications.
* **Consul:** Acts as a service discovery and configuration solution. It allows the system to locate other services
  dynamically in a distributed architecture, handle load balancing, and store service configuration centrally. This
  ensures that the components of the system can communicate efficiently and scale seamlessly.
* **Nginx Reverse Proxy:** Acts as a reverse proxy server that handles load balancing, SSL termination, caching,
  authentication, and monitoring of API traffic. It's used in this project to manage and expose the services in a unified
way, ensuring secure and efficient communication between clients and microservices.

## Workflows

### Functional workflow

![Functional workflow](doc/functional-workflow.png "Functional workflow")


### Hateoas workflow

![Hateoas workflow](doc/hateoas-links.png "Hateoas workflow")

## Quick Start

Follow these steps to set up and run the project on your machine.

### Prerequisites

Make sure you have the following installed:
* **Docker runner**
* **Java Development Kit (JDK):** Version 21 recommended.
* **Gradle:** Version 8.10 or higher.
* **Your preferred IDE:** IntelliJ IDEA, Eclipse, etc. (optional).
* **An HTTP client:** Postman, curl, etc. to interact with the API.

### Installation

1.  Clone the GitHub repository:
    ```bash
    git clone [https://github.com/salimchami/hateoas-at-scale.git](https://github.com/salimchami/hateoas-at-scale.git)
    cd hateoas-at-scale
    ```

### Execution

1.  Run the start.sh shell script:
    ```bash
    ./start.sh
    ```
    Alternatively, you can run the main classes (`*Application.java`) from your IDE by editing the runner configuration to run it on docker.

2. Consul service should be available at [localhost:8500](http://localhost:8500)
On the Consul web page, you can find services urls

3. Nginx should be available at [localhost:8020](http://localhost:8020)

## Usage

### Request and Response Examples

Here's an example of the Ada Lovelace cart response 

**Example Request (GET <nginx-ui-url>/carts-service/api/v1/carts/my-cart):**

``` json
{
    "totalPrice": 1199.00,
    "user": {
        "username": "ada.lovelace"
    },
    "products": [
        {
            "name": "apple",
            "totalPrice": 1.00,
            "quantity": 1,
            "_links": {
                "self": {
                    "href": "http://172.25.0.6:8080/api/v1/products/apple"
                },
                "addProductToCart": {
                    "href": "http://localhost:8020/carts-service/api/v1/cart/add-product"
                },
                "myCart": {
                    "href": "http://localhost:8020/carts-service/api/v1/cart/my-cart"
                }
            }
        },
        {
            "name": "pineapple",
            "totalPrice": 198.00,
            "quantity": 2,
            "_links": {
                "self": {
                    "href": "http://172.25.0.6:8080/api/v1/products/pineapple"
                },
                "addProductToCart": {
                    "href": "http://localhost:8020/carts-service/api/v1/cart/add-product"
                },
                "myCart": {
                    "href": "http://localhost:8020/carts-service/api/v1/cart/my-cart"
                }
            }
        },
        {
            "name": "watermelon",
            "totalPrice": 1000.00,
            "quantity": 1,
            "_links": {
                "self": {
                    "href": "http://172.25.0.6:8080/api/v1/products/watermelon"
                },
                "addProductToCart": {
                    "href": "http://localhost:8020/carts-service/api/v1/cart/add-product"
                },
                "myCart": {
                    "href": "http://localhost:8020/carts-service/api/v1/cart/my-cart"
                }
            }
        }
    ],
    "_links": {
        "self": {
            "href": "http://localhost:8020/carts-service/api/v1/cart/my-cart"
        }
    }
}
```

## Contributions

Contributions are welcome!

In summary, you can contribute by:

* Reporting issues (bugs, enhancements).
* Proposing improvements or new features.
* Submitting pull requests with fixes or additions.

## License
This project is distributed under the MIT License. See the LICENSE file for more details.

## Contact
You can contact me at the following:

* Name: Salim Chami
* Email: [it@salimchami.net](mailto:it@salimchami.net)
* GitHub: [https://github.com/salimchami/](https://github.com/salimchami/)
