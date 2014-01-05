/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wmh.project.graph.model;

import junit.framework.TestCase;

/**
 *
 * @author MAREK
 */
public class GraphTest extends TestCase{
    
    public void testCreateSimpleGraph(){
        Graph graph = simple();
        
        assertNotNull(graph.getNode("A"));
        assertNotNull(graph.getNode("B"));
        assertNotNull(graph.getNode("C"));
        
        Node a = graph.getNode("A");
        Node b = graph.getNode("B");
        Node c = graph.getNode("C");
        
        assertEquals(c, a.getNode("C"));
        assertEquals(a, c.getNode("A"));
        assertEquals(b, a.getNode("B"));
        assertEquals(a, b.getNode("A"));
        assertNull(b.getNode("C"));
        assertNull(c.getNode("B"));
        assertTrue(graph.areConnected("A", "B"));
        assertTrue(graph.areConnected("A", "C"));
        assertFalse(graph.areConnected("B", "C"));
    }
    
    public void testLoadGraph(){
        Graph graph = GraphUtil.loadGraph("./res/graph_example1.xml");
        
        assertNotNull(graph.getNode("A"));
        assertNotNull(graph.getNode("B"));
        assertNotNull(graph.getNode("C"));
        
        Node a = graph.getNode("A");
        Node b = graph.getNode("B");
        Node c = graph.getNode("C");
        
        assertEquals(c, a.getNode("C"));
        assertEquals(a, c.getNode("A"));
        assertEquals(b, a.getNode("B"));
        assertEquals(a, b.getNode("A"));
        assertNull(b.getNode("C"));
        assertNull(c.getNode("B"));
        assertTrue(graph.areConnected("A", "B"));
        assertTrue(graph.areConnected("A", "C"));
        assertFalse(graph.areConnected("B", "C"));
    }
    
    public void testRemoveRel(){
        Graph graph = simple();
        
        graph.removeRelation("A", "B");
        
        assertNotNull(graph.getNode("A"));
        assertNotNull(graph.getNode("B"));
        assertNotNull(graph.getNode("C"));
        
        Node a = graph.getNode("A");
        Node b = graph.getNode("B");
        Node c = graph.getNode("C");
        
        assertEquals(c, a.getNode("C"));
        assertEquals(a, c.getNode("A"));
        assertNull(a.getNode("B"));
        assertNull(b.getNode("A"));
        assertNull(b.getNode("C"));
        assertNull(c.getNode("B"));
        assertFalse(graph.areConnected("A", "B"));
        assertTrue(graph.areConnected("A", "C"));
        assertFalse(graph.areConnected("B", "C"));
    }
    
    private Graph simple(){
        Graph graph = new Graph("TEST");
        
        Node a = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        
        graph.addNode(a);
        graph.addNode(b);
        graph.addNode(c);
        graph.createRelation(a.getLabel(), b.getLabel());
        graph.createRelation(a.getLabel(), c.getLabel());
        return graph;
    }
}
