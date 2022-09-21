package no.fintlabs.random

import no.fintlabs.MyUtil
import spock.lang.Specification

class MyRandomUtilSpec extends Specification {

    MyUtil myUtil

    def setup() {
        myUtil = new MyUtil()
    }

    def "Adding element"() {
        given:
        myUtil.set(1)

        when:
        var result = myUtil.add(1)

        then:
        result == 2
    }

    def "Compact syntax also work"() {
        given:
        myUtil.set(1)

        expect:
        myUtil.add(3) == 4
    }

    //@Ignore
    def "adding two number"() {
        given:
        myUtil.set(a)

        expect:
        myUtil.add(b) == c

        where:
        a | b | c
        1 | 1 | 2
        5 | 4 | 9
        9 | 9 | 18
    }
}
