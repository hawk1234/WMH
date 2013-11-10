/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wmh.project.graph.model;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author MAREK
 */
public class GraphUtil {
    
    public static final String GRAPH_TAG="graph";
    public static final String NODE_TAG="node";
    public static final String NODE_LABEL_ATT="label";
    public static final String RELATION_TAG="realtion";
    public static final String RELATION_N1_ATT="node1";
    public static final String RELATION_N2_ATT="node2";
    
    
    private GraphUtil(){}
    
    public static void saveGraph(Graph graph, String destination){
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // save
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement(GRAPH_TAG);
            doc.appendChild(rootElement);
            
            for(Node n : graph.getNodes()){
                Element nodeEl = doc.createElement(NODE_TAG);
                nodeEl.setAttribute(NODE_LABEL_ATT, n.getLabel());
                rootElement.appendChild(nodeEl);
            }
            for(Relation r : graph.getRelations()){
                Element relEl = doc.createElement(RELATION_TAG);
                relEl.setAttribute(RELATION_N1_ATT, r.getNode1().getLabel());
                relEl.setAttribute(RELATION_N2_ATT, r.getNode2().getLabel());
                rootElement.appendChild(relEl);
            }
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(destination));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
        } catch (TransformerException ex) {
            throw new GraphException("Unable to write: "+destination, ex);
        } catch (ParserConfigurationException ex) {
            throw new GraphException("Unable to write: "+destination, ex);
        }
    }
    
    public static Graph loadGraph(String location){
        Graph ret = new Graph();
        try {
            File fXmlFile = new File(location);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            
            //load
            Element el = doc.getDocumentElement();
            NodeList nodes = el.getElementsByTagName(NODE_TAG);
            for(int i=0; i<nodes.getLength(); ++i){
                Element n = (Element) nodes.item(i);
                String label = n.getAttribute(NODE_LABEL_ATT);
                ret.addNode(new Node(label));
            }
            NodeList relations = el.getElementsByTagName(RELATION_TAG);
            for(int i=0; i<relations.getLength(); ++i){
                Element r = (Element) relations.item(i);
                String label1 = r.getAttribute(RELATION_N1_ATT);
                String label2 = r.getAttribute(RELATION_N2_ATT);
                ret.createRelation(label1, label2);
            }
        } catch (SAXException ex) {
            throw new GraphException("Unable to load: "+location, ex);
        } catch (IOException ex) {
            throw new GraphException("Unable to load: "+location, ex);
        } catch (ParserConfigurationException ex) {
            throw new GraphException("Unable to load: "+location, ex);
        }
        return ret;
    }
}
