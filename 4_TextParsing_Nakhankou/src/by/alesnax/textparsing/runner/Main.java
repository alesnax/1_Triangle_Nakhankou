package by.alesnax.textparsing.runner;

import by.alesnax.textparsing.entity.TextComponent;
import by.alesnax.textparsing.logic.FileParser;
import by.alesnax.textparsing.logic.TextOperation;
import by.alesnax.textparsing.logic.TextReconstructor;
import by.alesnax.textparsing.writer.TextWriter;

import static by.alesnax.textparsing.constants.FileConstant.*;

// static import

/**
 * Created by alesnax on 02.11.2016.
 */
public class Main {

    public static void main(String[] args) throws CloneNotSupportedException {
        // 1.Reading and parsing text from file
        FileParser fileParser = new FileParser();
        TextComponent text = fileParser.parseText(TEXT_FILE_EX);

        // 2. Joining text from textcomponent
        TextReconstructor reconstructor = new TextReconstructor();
        String parsedText = reconstructor.getParsedText(text);

        // 3.  Writing file to file
        TextWriter writer = new TextWriter();
        writer.writeTxtFile(TEXT_RESULT, parsedText);


        TextOperation operation = new TextOperation();


        // 4. Task 1(8): Transform each lexeme in the text deleting letters which are the same as the first.
        TextComponent text1 = fileParser.parseText(TEXT_FILE_EX);
        operation.deleteNextLetters(text1);
        String parsedText1 = reconstructor.getParsedText(text1);
        writer.writeTxtFile(TEXT_RESULT_1, parsedText1);


        // 5. Task 2(4): Print all lexemes in alphabetic order. Lexemes with another first letter must be printed at new line.
        TextComponent text2 = fileParser.parseText(TEXT_FILE_EX);
        String parsedText2 = operation.printLexemesInAlphabeticalOrder(text2);
        writer.writeTxtFile(TEXT_RESULT_2, parsedText2);

        // 6. Task 3(2): Print all sentences sorted by lexeme number
        TextComponent text3 = fileParser.parseText(TEXT_FILE_EX);
        String parsedText3 = operation.printSentencesSortedByLexemeNumber(text3);
        writer.writeTxtFile(TEXT_RESULT_3, parsedText3);
    }

}
