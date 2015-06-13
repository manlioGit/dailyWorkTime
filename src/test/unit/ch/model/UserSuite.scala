package ch.model

import conf.TestSpec

class UserSuite extends TestSpec{

  test("create a User"){
    val user = User("name")
    
//    assert(user.role == Role.NORMAL)
  }

}