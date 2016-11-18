package by.alesnax.textparsing.logic;

import by.alesnax.textparsing.entity.TextComponent;
import by.alesnax.textparsing.entity.TextComposite;
import by.alesnax.textparsing.entity.TextLeaf;
import by.alesnax.textparsing.type.TextElemType;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

/**
 * Created by alesnax on 14.11.2016.
 */

public class TextOperation {
    private static Logger logger = LogManager.getLogger(TextOperation.class);

    public static final String REGEX_SQUARE_SCOPES = "[\\[\\]]";
    public static final String EMPTY_SYMBOL = "";
    public static final String TAB = "\t";
    public static final char NEW_LINE = '\n';
    public static final String END_ELEMENT = "$";

    //method that transform each lexeme in the text deleting other letters which are the same as the first letter
    public void deleteNextLetters(TextComponent component) {
        TextComposite text = (TextComposite) component;

        for (TextComponent comp : text.getElements()) {
            if (comp instanceof TextComposite) {
                deleteNextLetters(comp);
            } else {
                TextLeaf leaf = (TextLeaf) comp;
                if (leaf.getElemType() == TextElemType.WORD) {
                    String word = leaf.getContent();
                    String lowWord = word.toLowerCase();
                    StringBuilder sb = new StringBuilder(word);
                    char first = word.toLowerCase().charAt(0);
                    for (int i = word.length() - 1; i > 0; i--) {
                        if (first == lowWord.charAt(i)) {
                            sb.deleteCharAt(i);
                        }
                    }
                    String result = sb.toString();
                    leaf.setContent(result);
                }
            }
        }
    }

    //method that printed all lexemes in alphabetic order. Lexemes with another first letter must be printed at new line.
    public String printLexemesInAlphabeticalOrder(TextComponent component) {
        TextComposite text = (TextComposite) component;
        ArrayList<String> words = findAllWords(text);
        StringBuilder sb = new StringBuilder();
        //sorting words by first letter
        words.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.toLowerCase().charAt(0) < o2.toLowerCase().charAt(0)) {
                    return -1;
                } else if (o1.toLowerCase().charAt(0) > o2.toLowerCase().charAt(0)) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        words.add(END_ELEMENT);
        char first = words.get(0).toLowerCase().charAt(0);
        int firstPos = 0;
        for (int i = 0; i < words.size(); i++) {
            char current = words.get(i).toLowerCase().charAt(0);
            if (first != current) {
                words.subList(firstPos, i).sort(Comparator.naturalOrder());
                sb.append(TAB).append(words.subList(firstPos, i)).append(NEW_LINE);
                first = current;
                firstPos = i;
            }
        }
        String result = sb.deleteCharAt(sb.length() - 1).toString().replaceAll(REGEX_SQUARE_SCOPES, EMPTY_SYMBOL);
        logger.log(Level.INFO, "Sorted text:\n" + result);
        return result;
    }

    public String printSentencesSortedByLexemeNumber(TextComponent component) {
        HashMap<String, Integer> sentences = findSentences(component);


        ArrayList<String> sortedSentences = new ArrayList<>();
        ArrayList<Integer> positions = new ArrayList<>();

        Iterator<Map.Entry<String, Integer>> it = sentences.entrySet().iterator();

        while (it.hasNext()) {
            boolean inserted = false;
            Map.Entry<String, Integer> entry = it.next();
            int amount = entry.getValue();
            String sent = entry.getKey();

            while (true) {
                if (positions.isEmpty()) {
                    positions.add(amount);
                    sortedSentences.add(sent);
                    break;
                }
                for (int i = 0; i < positions.size(); i++) {
                    if (amount <= positions.get(i)) {
                        positions.add(i, amount);
                        sortedSentences.add(i, sent);
                        inserted = true;
                    }
                    if (inserted) {
                        break;
                    }
                }
                if (!inserted) {
                    positions.add(amount);
                    sortedSentences.add(sent);
                    inserted = false;
                }
                break;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sortedSentences.size(); i++) {
            sb.append(sortedSentences.get(i)).append("\n");
        }
        String result = sb.deleteCharAt(sb.length() - 1).toString();
        logger.log(Level.INFO, "Sorted sentences by lexemes amount: \n" + result);
        return result;
    }

    private HashMap<String, Integer> findSentences(TextComponent component) {
        HashMap<String, Integer> sentences = new HashMap<>();
        TextReconstructor reconstructor = new TextReconstructor();
        TextComposite text = (TextComposite) component;

        //find and join sentences with end mark
        for (TextComponent comp : text.getElements()) {// comp == paragraph or tab
            if (comp instanceof TextComposite && ((TextComposite) comp).getElemType() == TextElemType.PARAGRAPH) {
                ArrayList<TextComponent> sentElements = ((TextComposite) comp).getElements();//sentElements == sentences or end mark
                for (int i = 0; i < sentElements.size(); i = i + 2) {
                    String sentence = reconstructor.getParsedText(sentElements.get(i));
                    TextLeaf endMark = (TextLeaf) sentElements.get(i + 1);
                    String punct = endMark.toString();
                    sentence = sentence.concat(endMark.toString().substring(0, punct.length() - 1));
                    int lexemeNumber = countLexemesInSentence(sentElements.get(i));
                    sentences.put(sentence, lexemeNumber);
                }
            }
        }
        return sentences;
    }

    private int countLexemesInSentence(TextComponent component) {
        TextComposite sentence = (TextComposite) component;
        int lexemeNumber = 0;
        for (int i = 0; i < sentence.getElements().size(); i++) {
            TextComposite elem = (TextComposite) sentence.getElements().get(i);//elem == lexeme
            for (int j = 0; j < elem.getElements().size(); j++) {
                TextLeaf leaf = (TextLeaf) elem.getElements().get(j);
                if (leaf.getElemType() == TextElemType.WORD || leaf.getElemType() == TextElemType.MATH_EXP) {
                    lexemeNumber++;
                }
            }
        }
        return lexemeNumber;
    }


    private ArrayList<String> findAllWords(TextComposite text) {
        ArrayList<String> words = new ArrayList<>();
        for (TextComponent comp : text.getElements()) {
            ArrayList<String> sublist = null;
            if (comp instanceof TextLeaf) {
                TextLeaf leaf = (TextLeaf) comp;
                if ((leaf.getElemType() == TextElemType.WORD)) {
                    words.add(leaf.getContent());
                }
            } else {
                sublist = findAllWords((TextComposite) comp);
                words.addAll(sublist);
            }
        }
        return words;
    }
}
