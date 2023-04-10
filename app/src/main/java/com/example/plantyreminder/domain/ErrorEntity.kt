package com.example.plantyreminder.domain

//sealed class ErrorEntity() {
//    object Network : ErrorEntity()
//    object AccessDenied : ErrorEntity()
//    object ServiceUnavailable : ErrorEntity()
//    sealed class DatabaseException : ErrorEntity() {
//        object ConstraintException : DatabaseException()
//        object CantOpenException: DatabaseException()
//    }
//    object Unknown : ErrorEntity()
//}
//
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
        NoInternet("You have no internet connection")
    }
}
