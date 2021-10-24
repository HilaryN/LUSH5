package com.example.lush5

import junit.framework.TestCase
import org.junit.Test


class LaunchAdapterTest : TestCase() {

    var mLaunches2Items = arrayOf<Launch?>(Launch("Rocket", "20200314", true, null),
        Launch("Butterfly", "20200506", false, null))

    public override fun setUp() {
        super.setUp()
    }

    public override fun tearDown() {}

    @Test
    fun testGetItemCountIsZeroOnNullArray() {
        val adapter = LaunchAdapter(null)
        val numItems = adapter.itemCount
        assertEquals(0, numItems)
    }

    @Test
    fun testGetItemCount() {
        val adapter = LaunchAdapter(mLaunches2Items)
        val numItems = adapter.itemCount
        assertEquals(2, numItems)
    }

    fun testOnBindViewHolder() {}

    fun testOnCreateViewHolder() {}
}