package iisi.parses

import iisi.parsers.DescriptionFileException
import iisi.parsers.DescriptionParser
import spock.lang.Specification

class DescriptionParserSpec extends Specification {

    def "Should return polynomial dimension from description file" () {
        given:
        File descriptionFile = new File(getClass().getClassLoader().getResource("description.txt").getPath())
        when:
        Integer degree = new DescriptionParser().getPolynomialDegree(descriptionFile)
        then:
        degree == 15
    }

    def "Should return polynomial degree from description file" () {
        given:
        File descriptionFile = new File(getClass().getClassLoader().getResource("description.txt").getPath())
        when:
        Integer degree = new DescriptionParser().getPolynomialDimension(descriptionFile)
        then:
        degree == 10
    }

    def "Should throw exception when reading polynomial degree from description file" () {
        given:
        File descriptionFile = new File(getClass().getClassLoader().getResource("description_first_line_empty.txt").getPath())
        when:
        new DescriptionParser().getPolynomialDegree(descriptionFile)
        then:
        DescriptionFileException exceptionThrown = thrown()
        exceptionThrown.message == "First line of description.txt file was found empty. Please provide description.txt file in correct form."

    }

    def "Should throw exception when reading polynomial dimension from description file" () {
        given:
        File descriptionFile = new File(getClass().getClassLoader().getResource("description_first_line_empty.txt").getPath())
        when:
        new DescriptionParser().getPolynomialDimension(descriptionFile)
        then:
        def exceptionThrown = thrown(DescriptionFileException)
        exceptionThrown.message == "First line of description.txt file was found empty. Please provide description.txt file in correct form."
    }

    def "test" () {
        //TODO:finish unittest for obtaining coefficients and components!!!!
        given:
        File descriptionFile = new File(getClass().getClassLoader().getResource("description.txt").getPath())
        when:
        new DescriptionParser().getComponentsFromFile(descriptionFile)
        then:
        2 == 2

    }
}