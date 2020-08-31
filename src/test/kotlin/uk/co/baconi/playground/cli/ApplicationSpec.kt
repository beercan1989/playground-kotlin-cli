package uk.co.baconi.playground.cli

import io.kotest.core.spec.style.StringSpec

class ApplicationSpec : StringSpec({
    "Hello World" {
        main(emptyArray())
    }
})