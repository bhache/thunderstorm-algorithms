package cz.cuni.lf1.thunderstorm.datastructures.extensions

import cz.cuni.lf1.thunderstorm.algorithms.padding.ZeroPadding
import cz.cuni.lf1.thunderstorm.test.assertGrayScaleImageEquals
import org.junit.Test

internal class GrayScaleImageTests {

    @Test
    public fun testRotateLeft() {
        val image = createGrayScaleImage(arrayOf(
                arrayOf(1.0, 2.0, 3.0),
                arrayOf(4.0, 5.0, 6.0)))

        val expected = createGrayScaleImage(arrayOf(
                arrayOf(3.0, 6.0),
                arrayOf(2.0, 5.0),
                arrayOf(1.0, 4.0)))

        val result = image.rotateLeft()

        assertGrayScaleImageEquals(expected, result, 0.0)
    }

    @Test
    public fun testRotateRight() {
        val image = createGrayScaleImage(arrayOf(
                arrayOf(1.0, 2.0, 3.0),
                arrayOf(4.0, 5.0, 6.0)))

        val expected = createGrayScaleImage(arrayOf(
                arrayOf(4.0, 1.0),
                arrayOf(5.0, 2.0),
                arrayOf(6.0, 3.0)))

        val result = image.rotateRight()

        assertGrayScaleImageEquals(expected, result, 0.0)
    }

    @Test
    public fun testConvolve2DSymmetric() {
        val image = createGrayScaleImage(arrayOf(
                arrayOf(1.0, 2.0, 3.0),
                arrayOf(4.0, 5.0, 6.0),
                arrayOf(7.0, 8.0, 9.0)))

        val kernel = createGrayScaleImage(create2DDoubleArray(3, 3, 1.0))

        val expected = createGrayScaleImage(arrayOf(
                arrayOf(12.0, 21.0, 16.0),
                arrayOf(27.0, 45.0, 33.0),
                arrayOf(24.0, 39.0, 28.0)))

        val result = image.convolve2D(kernel, ::ZeroPadding)

        assertGrayScaleImageEquals(expected, result, 0.0)
    }

    @Test
    public fun testConvolve2DAsymmetric() {
        val image = createGrayScaleImage(arrayOf(
                arrayOf(1.0, 2.0, 3.0),
                arrayOf(4.0, 5.0, 6.0),
                arrayOf(7.0, 8.0, 9.0)))

        val kernel = createGrayScaleImage(arrayOf(arrayOf(1.0, 2.0, 3.0)))

        val expected = createGrayScaleImage(arrayOf(
                arrayOf(4.0, 10.0, 12.0),
                arrayOf(13.0, 28.0, 27.0),
                arrayOf(22.0, 46.0, 42.0)))

        val result = image.convolve2D(kernel, ::ZeroPadding)

        assertGrayScaleImageEquals(expected, result, 0.0)
    }

    @Test
    public fun testConvolve2DAsymmetricImage1() {
        val image = createGrayScaleImage(arrayOf(
                arrayOf(1.0, 2.0, 3.0),
                arrayOf(4.0, 5.0, 6.0)))

        val kernel = createGrayScaleImage(create2DDoubleArray(3, 3, 1.0))

        val expected = createGrayScaleImage(arrayOf(
                arrayOf(12.0, 21.0, 16.0),
                arrayOf(12.0, 21.0, 16.0)))

        val result = image.convolve2D(kernel, ::ZeroPadding)

        assertGrayScaleImageEquals(expected, result, 0.0)
    }

    @Test
    public fun testConvolve2DAsymmetricImage2() {
        val image = createGrayScaleImage(arrayOf(
                arrayOf(1.0, 2.0),
                arrayOf(3.0, 4.0),
                arrayOf(5.0, 6.0)))

        val kernel = createGrayScaleImage(create2DDoubleArray(3, 3, 1.0))

        val expected = createGrayScaleImage(arrayOf(
                arrayOf(10.0, 10.0),
                arrayOf(21.0, 21.0),
                arrayOf(18.0, 18.0)))

        val result = image.convolve2D(kernel, ::ZeroPadding)

        assertGrayScaleImageEquals(expected, result, 0.0)
    }

    @Test
    public fun testDilate() {
        val image = createGrayScaleImage(arrayOf(
                arrayOf(9.0, 9.0, 7.0, 7.0, 6.0),
                arrayOf(4.0, 6.0, 7.0, 5.0, 6.0),
                arrayOf(1.0, 1.0, 1.0, 1.0, 1.0),
                arrayOf(2.0, 3.0, 4.0, 3.0, 2.0),
                arrayOf(2.0, 3.0, 3.0, 3.0, 2.0)))

        val kernel = createGrayScaleImage(create2DDoubleArray(3, 3, 1.0))

        val expected = createGrayScaleImage(arrayOf(
                arrayOf(9.0, 9.0, 9.0, 7.0, 7.0),
                arrayOf(9.0, 9.0, 9.0, 7.0, 7.0),
                arrayOf(6.0, 7.0, 7.0, 7.0, 6.0),
                arrayOf(3.0, 4.0, 4.0, 4.0, 3.0),
                arrayOf(3.0, 4.0, 4.0, 4.0, 3.0)))

        val result = image.dilate(kernel)

        assertGrayScaleImageEquals(expected, result, 0.0)
    }
}