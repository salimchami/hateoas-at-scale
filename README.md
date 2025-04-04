# hateoas-at-scale

[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE.md)
![Last Commit](https://img.shields.io/github/last-commit/salimchami/hateoas-at-scale)
![Number of Contributors](https://img.shields.io/github/contributors/salimchami/hateoas-at-scale)
![Main Language](https://img.shields.io/github/languages/top/salimchami/hateoas-at-scale)

**A demonstration of HATEOAS implementation at scale with Spring Boot.**

This project aims to showcase an approach to implement HATEOAS (Hypermedia as the Engine of Application State) in RESTful applications designed for scalability. It provides a concrete example of an architecture and techniques for managing hypermedia links efficiently in distributed systems.

## Table of Contents

* [Key Features](#key-features)
* [Quick Start](#quick-start)
    * [Prerequisites](#prerequisites)
    * [Installation](#installation)
    * [Execution](#execution)
* [Usage](#usage)
    * [API Endpoints](#api-endpoints)
    * [Request and Response Examples](#request-and-response-examples)
* [Contributions](#contributions)
* [License](#license)
* [Contact](#contact)

## Key Features

* **HATEOAS Implementation:** Utilizes Spring HATEOAS features to dynamically generate hypermedia links in API responses.
* **Scalability-focused Design:** Explores strategies for managing HATEOAS links in a microservices or distributed system context.
* **Concrete Example:** Provides an example of a simple API (e.g., cart management) integrating HATEOAS.
* **Spring Boot Usage:** Simplifies the setup and execution of the application.

## Quick Start

Follow these steps to set up and run the project on your machine.

### Prerequisites

Make sure you have the following installed:
* **Docker runner**
* **Java Development Kit (JDK):** Version 21 recommended.
* **Gradle:** Version 8.10 or higher.
* **Your preferred IDE:** IntelliJ IDEA, Eclipse, etc. (optional).
* **An HTTP client:** Postman, Insomnia, curl, etc. to interact with the API.

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

3. On the Consul web page, you can find services urls
 
## Usage

### API Endpoints

List the main API endpoints available and their roles. For example:

* `GET /users/1`: Retrieves a user from the `users` service. (see `com.hateoasatscale.users.infrastructure.driven.FakeDbUsers`)
* `GET /products/1`: Retrieves a product from the `products` service. (see `com.hateoasatscale.products.infrastructure.driven.FakeDbProducts`)
* `GET /cart/1`: Retrieves the user 1 cart from the `cart` service. (see `com.hateoasatscale.cart.infrastructure.driven.db.FakeDbCarts`)

### Request and Response Examples

Here's an example of the cart 1 response 

**Example Request (GET /resources):**

```bash
curl http://<cart-service-url>/cart/1
```

``` json
{
    "totalPrice": 159.00,
    "user": {
        "firstname": "Ada",
        "lastname": "Lovelace",
        "_links": {
            "self": {
                "href": "http://172.20.0.4:8081/users/1"
            }
        }
    },
    "products": [
        {
            "name": "apple",
            "reference": "RRDGHGT554346",
            "price": 1.00,
            "_links": {
                "self": {
                    "href": "http://172.20.0.5:8082/products/1"
                }
            }
        },
        {
            "name": "orange",
            "reference": "DFHDHYUJJBN887644",
            "price": 158.00,
            "_links": {
                "self": {
                    "href": "http://172.20.0.5:8082/products/4"
                }
            }
        }
    ],
    "_links": {
        "self": {
            "href": "http://172.20.0.3:8080/cart/1"
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