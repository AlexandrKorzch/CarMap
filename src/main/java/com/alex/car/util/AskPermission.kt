package com.alex.car.util

class AskPermission(
        val function: (Boolean) -> Unit,
        val permissions: Array<String>) {
}
