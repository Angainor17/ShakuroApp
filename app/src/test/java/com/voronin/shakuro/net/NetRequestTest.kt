package com.voronin.shakuro.net

import org.junit.runner.RunWith
import org.powermock.core.classloader.annotations.PowerMockIgnore
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@PowerMockIgnore(value = ["org.mockito.*", "org.robolectric.*", "android.*"])
@Config(sdk = [26])
abstract class NetRequestTest {


}