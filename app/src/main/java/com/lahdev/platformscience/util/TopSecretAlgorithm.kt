package com.lahdev.platformscience.util

class TopSecretAlgorithm {

    fun calculateSuitabilityScore(driverName: String, streetName: String): Double {
        val vowels = setOf('a', 'e', 'i', 'o', 'u')
        val destinationStreetName = streetName.split("Suite ")[0].split("Apt.")[0]
        val driverNameLength = driverName.filter { it.isLetter() }.length

        val baseSS: Double = if (destinationStreetName.length % 2 == 0) {
            //If the length of the shipment's destination street name is even, the base suitability score (SS) is
            //the number of vowels in the driver’s name multiplied by 1.5.
            driverName.filter { it.lowercaseChar() in vowels }.length * 1.5
        } else {//Odd
            //If the length of the shipment's destination street name is odd, the base SS is the number of
            //consonants in the driver’s name multiplied by 1.
            driverName.filter { it.lowercaseChar() !in vowels }.length * 1.0//Required so it can be double
        }

        // If the length of the shipment's destination street name shares any common factors (besides 1)
        //with the length of the driver’s name, the SS is increased by 50% above the base SS.
        val commonFactors =
            (2..driverNameLength).filter { factor -> driverNameLength % factor == 0 && destinationStreetName.length % factor == 0 }
        val hasCommonFactors = commonFactors.isNotEmpty()

        // Increase SS by 50% if there are common factors
        return if (hasCommonFactors) {
            baseSS * 1.5
        } else {
            baseSS
        }
    }
}
