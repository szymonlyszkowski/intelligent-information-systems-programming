package iisi.parsers;

import iisi.polynomial.PolynomialTerm;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DescriptionParser {
    private static final int POLYNOMIAL_DIMENSION_ELEMENT_INDEX = 0;
    private static final int POLYNOMIAL_DEGREE_ELEMENT_INDEX = 1;
    private static final int AMOUNT_OF_LINES_TO_SKIP_DIMENSION_AND_DEGREE = 1;

    public Integer getPolynomialDegree(File descriptionFile) throws IOException {
        return Integer.parseInt(getElementsFromFirstLineInFile(descriptionFile)[POLYNOMIAL_DEGREE_ELEMENT_INDEX]);
    }

    public Integer getPolynomialDimension(File descriptionFile) throws IOException {
        return Integer.parseInt(getElementsFromFirstLineInFile(descriptionFile)[POLYNOMIAL_DIMENSION_ELEMENT_INDEX]);
    }

    private String[] getElementsFromFirstLineInFile(File descriptionFile) throws DescriptionFileException, IOException {
        Stream<String> lines = Files.lines(descriptionFile.toPath());
        return checkLineCorrectness(lines.findFirst());
    }

    private String[] checkLineCorrectness(Optional<String> firstLine) {
        return firstLine.filter(s -> !s.isEmpty()).
                map(splitLineBySpace()).
                orElseThrow(DescriptionFileException::new);
    }

    private Stream<String[]> getPolynomialTermsData(File descriptionFile) throws IOException {
        Stream<String> lines = Files.lines(descriptionFile.toPath());
        return lines.skip(AMOUNT_OF_LINES_TO_SKIP_DIMENSION_AND_DEGREE).map((splitLineBySpace()));
    }

    private PolynomialTerm constructPolynomialTerm(String[] termData){
        Double coefficient = Double.parseDouble(termData[termData.length-1]);
        PolynomialTerm term = new PolynomialTerm(coefficient);
        Arrays.asList(termData).stream().skip(termData.length-1).map(variableId->term.updatePolynomialTerm(Integer.parseInt(variableId)));
        return term;
    }

    public List<PolynomialTerm> getPolynomialTerms(File descriptionFile) throws IOException {
        Stream<String[]> polynomialTermsData = getPolynomialTermsData(descriptionFile);
        return polynomialTermsData
                .map(dataLine -> constructPolynomialTerm(dataLine)).collect(Collectors.toList());

    }

    private Function<String, String[]> splitLineBySpace() {
        return l -> l.split("\\s");
    }
}
