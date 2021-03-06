package cz.cuni.lf1.thunderstorm.datastructures.extensions

import cz.cuni.lf1.thunderstorm.datastructures.Distance
import cz.cuni.lf1.thunderstorm.datastructures.Intensity
import cz.cuni.lf1.thunderstorm.datastructures.Molecule

public fun createMoleculeDetection(x: Double, y: Double)
        = Molecule(Distance.fromPixels(x), Distance.fromPixels(y))

public fun createMoleculeDetection(x: Double, y: Double, intensity: Double)
        = Molecule(Distance.fromPixels(x), Distance.fromPixels(y)).also {
            it.intensity = Intensity.fromAdCounts(intensity)
        }

public fun createMoleculeDetectionNano(x: Double, y: Double)
        = Molecule(Distance.fromNanoMeters(x), Distance.fromNanoMeters(y))

public fun createMoleculeEstimate(x: Double, y: Double, intensity: Double, sigma: Double, offset: Double)
        = Molecule(Distance.fromPixels(x), Distance.fromPixels(y)).also {
    it.intensity = Intensity.fromAdCounts(intensity)
    it.sigma = Distance.fromPixels(sigma)
    it.offset = Intensity.fromAdCounts(offset)
}