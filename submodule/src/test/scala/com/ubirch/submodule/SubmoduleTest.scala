package com.ubirch.submodule

import org.scalatest.{BeforeAndAfterAll, FlatSpec, Matchers}

class SubmoduleTest extends FlatSpec with Matchers with BeforeAndAfterAll {
  "submodule" should "pass the test" in {
    "pass" should equal("pass")
  }
}