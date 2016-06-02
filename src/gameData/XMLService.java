package gameData;

import UserInterface.ScoreBoard;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.util.ArrayList;

public class XMLService {
    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
    Document document = documentBuilder.parse("src/gameData/GameData.xml");
    Element root = document.getDocumentElement();
    public XMLService() throws ParserConfigurationException, IOException, SAXException {

    }

    public void updateScoreBoard(String userName, String score, String lvlReached) throws TransformerException {
        ScoreBoard scores = new ScoreBoard(userName,score,lvlReached);
        Element scoreDetail = document.createElement("ScoreDetail");

        Element name = document.createElement("Name");
        name.appendChild(document.createTextNode(scores.getName()));
        scoreDetail.appendChild(name);

        Element score1 = document.createElement("Score");
        score1.appendChild(document.createTextNode(scores.getScore()));
        scoreDetail.appendChild(score1);

        Element levelReached = document.createElement("LevelReached");
        levelReached.appendChild(document.createTextNode(scores.getLvlReached()));
        scoreDetail.appendChild(levelReached);

        root.appendChild(scoreDetail);

        DOMSource source = new DOMSource(document);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        StreamResult result = new StreamResult("src/gameData/GameData.xml");
        transformer.transform(source, result);
    }

    public ArrayList<ScoreBoard> getScoreList() {
        ArrayList<ScoreBoard> scoreList = new ArrayList<>();

        try {
            document.getDocumentElement().normalize();
            NodeList nodeList = document.getElementsByTagName("ScoreDetail");

            for (int s = 0; s < nodeList.getLength(); s++) {
                Node fstNode = nodeList.item(s);

                if (fstNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) fstNode;

                    NodeList nameNodeList = element.getElementsByTagName("Name");
                    Element nameElement = (Element) nameNodeList.item(0);
                    NodeList names = nameElement.getChildNodes();

                    NodeList scoreNodeList = element.getElementsByTagName("Score");
                    Element scoreElement = (Element) scoreNodeList.item(0);
                    NodeList score = scoreElement.getChildNodes();

                    NodeList levelReachedNodeList = element.getElementsByTagName("LevelReached");
                    Element levelReachedElement = (Element) levelReachedNodeList.item(0);
                    NodeList levelReached = levelReachedElement.getChildNodes();

                    scoreList.add(new ScoreBoard((names.item(0)).getNodeValue(), (score.item(0)).getNodeValue(), (levelReached.item(0)).getNodeValue() ));
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return scoreList;
    }
}
