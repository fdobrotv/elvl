package com.fdobrotv.elvl.impl.util

import java.math.BigDecimal
import java.math.RoundingMode

fun bigDecimal(double: Double) = BigDecimal(double).setScale(2, RoundingMode.HALF_EVEN)