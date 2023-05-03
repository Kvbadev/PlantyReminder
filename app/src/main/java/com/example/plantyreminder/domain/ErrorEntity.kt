package com.example.plantyreminder.domain

sealed interface ErrorEntity {
    var message: String;

    enum class Default(override var message: String) : ErrorEntity {
        AccessDenied("Access Denied"),
        ServiceUnavailable("Service was unavailable"),
        Unknown("Error");
    }

    enum class Database(override var message: String) : ErrorEntity {
        ConstraintException("Value was not unique"),
        CantOpenException("Could not open the database");
    }

    enum class Network(override var message: String) : ErrorEntity {
        NoInternet("You have no internet connection"),
        InvalidHttpResponse("Could not obtain information from the API")
    }
}
