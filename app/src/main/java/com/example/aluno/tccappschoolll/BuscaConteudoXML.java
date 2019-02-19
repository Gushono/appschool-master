package com.example.aluno.tccappschoolll;

import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by douglas.gaspar on 07/05/2018.
 */

public class BuscaConteudoXML {
    private String retorno;
    private String elementoPrincipal;
    private NodeList nodes;

    public BuscaConteudoXML(String retorno, String elementoPrincipal) {
        this.retorno = retorno;
        this.elementoPrincipal = elementoPrincipal;
    }

    public NodeList elementosXML() {
        try {
            //Armazenamento do XML em um documento para que seja possível manipulá-lo
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            //Indicação de qual o conteúdo de entrada (no caso o XML)
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(retorno));

            //Conversão do conteúdo XML (String) para Document
            Document document = db.parse(is);

            //Indicação do ponto principal do XML
            nodes = document.getElementsByTagName(elementoPrincipal);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  nodes;
    }

    public String getValue(String tag, Element element) {
        //Pegando o valor de dentro de cada item/elemento
        NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodes.item(0);


        Log.i("testeInfo", "TAG: " + tag);
        Log.i("testeInfo", "Valor : " + node.getNodeValue() + "");

        return node.getNodeValue();
    }
}
