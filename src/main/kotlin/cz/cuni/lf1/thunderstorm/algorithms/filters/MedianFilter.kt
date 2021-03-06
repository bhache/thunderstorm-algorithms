package cz.cuni.lf1.thunderstorm.algorithms.filters

import cz.cuni.lf1.thunderstorm.datastructures.GrayScaleImage
import cz.cuni.lf1.thunderstorm.datastructures.GrayScaleImageImpl
import cz.cuni.lf1.thunderstorm.datastructures.extensions.create2DDoubleArray
import cz.cuni.lf1.thunderstorm.datastructures.extensions.median

public class MedianFilter(private val pattern: Pattern, private val size: Int) : FilterWithVariables {

    public enum class Pattern {
        CROSS,
        BOX
    }

    public override val variables = mapOf(
            "I" to emptyFilterVariable(),
            "F" to emptyFilterVariable())

    override fun filter(image: GrayScaleImage): GrayScaleImage {
        val f = when (pattern) {
            Pattern.BOX -> filterBox(image)
            Pattern.CROSS -> filterCross(image)
        }

        variables["I"]!!.setRefresher { image }
        variables["F"]!!.setRefresher { f }

        return f
    }

    private fun filterBox(image: GrayScaleImage): GrayScaleImage {
        val result = create2DDoubleArray(image.getHeight(), image.getWidth(), 0.0)

        for (y in 0..(image.getHeight() - 1)) {
            val y0 = y - size/2
            for (x in 0..(image.getWidth() - 1)) {
                val x0 = x - size/2

                result[y][x] = (y0..(y0 + size - 1)).filter { it >= 0 && it < image.getHeight() }
                        .flatMap { r -> (x0..(x0 + size - 1)).filter { it >= 0 && it < image.getWidth() }
                                .map { c -> image.getValue(r, c) } }
                        .toTypedArray()
                        .median()
            }
        }
        return GrayScaleImageImpl(result)
    }

    private fun filterCross(image: GrayScaleImage): GrayScaleImage {
        val result = create2DDoubleArray(image.getHeight(), image.getWidth(), 0.0)

        for (y in 0..(image.getHeight() - 1)) {
            val y0 = y - size/2
            for (x in 0..(image.getWidth() - 1)) {
                val x0 = x - size/2

                result[y][x] = ((x0..(x0 + size - 1)).filter { it >= 0 && it < image.getWidth() }.map { image.getValue(y, it) }
                              + (y0..(y0 + size - 1)).filter { it >= 0 && it < image.getHeight() && it != y }.map { image.getValue(it, x) })
                        .toTypedArray()
                        .median()
            }
        }
        return GrayScaleImageImpl(result)
    }
}
