package no.fintlabs

import spock.lang.Specification

class MyListSpec extends Specification{

    MyList myList

    def setup() {
        myList = new MyList()
    }

    def "Adding element"() {
        when:
        myList.add("This is a test")

        then:
        myList.size() == 1
        myList.peek() == "This is a test"
    }

    def "Fail if peek on empty"(){
        when: "I peek on a empty list"
        myList.peek()

        then: "exception should be thrown"
        thrown(IndexOutOfBoundsException)
    }

    def "Test that validator is called"() {
        given:
        MyListValidator validator = Mock()
        myList = new MyList(validator)

        when:
        myList.add("Hello")

        then:
        1 * validator.validate("Hello")
    }

    def "Test adding invalid value"() {
        given:
        MyListValidator validator = Stub()
        validator.validate(_) >> false
        myList = new MyList(validator)

        when:
        var result = myList.add("A valid value")

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
        var result = myList.add(null)

        then:
        result
        myList.size() == 1
    }

    def "Test example data"(){
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


}
