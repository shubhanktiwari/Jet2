package com.example.article.utils

import java.io.IOException

class NoInternetException(message: String) : IOException(message)
class NotFoundException(message: String) : IOException(message)
class UnAuthorizedException(message: String) : IOException(message)
class UnKnownException(message: String) : IOException(message)