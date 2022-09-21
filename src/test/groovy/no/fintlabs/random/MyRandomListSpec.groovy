package no.fintlabs.random

import no.fintlabs.MyList
import no.fintlabs.MyListValidator
import spock.lang.Specification

class MyRandomListSpec extends Specification {

    MyList myList

    def setup() {
        myList = new MyList()
    }

    def "Adding element"() {
        when:
        myList.add("This is a test");

        then:
        myList.size() == 1
        myList.peek() == "This is a test"
    }

    def "Fail if peek on empty"() {
        when: "I peek a empty list"
        myList.peek()

        then: "exception should be thrown"
        thrown(IndexOutOfBoundsException)
    }

    def "Test that validator is called"() {
        given:
        MyListValidator validator = Mock()
        myList = new MyList(validator)

        when:
        myList.add("hello")

        then:
        1 * validator.validate(_)
    }

//    1 * subscriber.receive("hello")      // exactly one call
//    0 * subscriber.receive("hello")      // zero calls
//    (1..3) * subscriber.receive("hello") // between one and three calls (inclusive)
//    (1.._) * subscriber.receive("hello") // at least one call
//    (_..3) * subscriber.receive("hello") // at most three calls

    def "Test adding invalid value"() {
        given:
        MyListValidator validator = Stub()
        validator.validate(_) >> false
        myList = new MyList(validator)

        when:
        var result = myList.add("Valid value")

        then:
        !result
        myList.size() == 0
    }

    def "Test adding valid value"() {
        given:
        MyListValidator validator = Stub()
        validator.validate(_) >> true
        myList = new MyList(validator)

        when:
        var result = myList.add("")

        then:
        result
        myList.size() == 1
    }

//    1 * subscriber.receive("hello")        // an argument that is equal to the String "hello"
//    1 * subscriber.receive(!"hello")       // an argument that is unequal to the String "hello"
//    1 * subscriber.receive()               // the empty argument list (would never match in our example)
//    1 * subscriber.receive(_)              // any single argument (including null)
//    1 * subscriber.receive(*_)             // any argument list (including the empty argument list)
//    1 * subscriber.receive(!null)          // any non-null argument
//    1 * subscriber.receive(_ as String)    // any non-null argument that is-a String

    def "Test example data"() {
        given:
        MyListValidator validator = Stub() {
            getExampleData() >> ["en", "to"]
        }
        myList = new MyList(validator)

        when:
        myList.loadExampleData()

        then:
        myList.size() == 2
        myList.peek() == "to"
    }

    // subscriber.receive(_) >> { throw new InternalError("ouch") }
}
