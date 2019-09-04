package com.yuniq.yuniq

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class YuniqApplication

fun main(args: Array<String>) {
	runApplication<YuniqApplication>(*args)
}
