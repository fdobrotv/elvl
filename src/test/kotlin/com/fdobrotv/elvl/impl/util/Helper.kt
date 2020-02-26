package com.fdobrotv.elvl.impl.util

import java.math.BigDecimal
import java.math.RoundingMode

fun bigDecimal(int: Number) = BigDecimal(int.toDouble()).setScale(2, RoundingMode.HALF_EVEN)