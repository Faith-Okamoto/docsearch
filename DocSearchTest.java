import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.junit.*;

public class DocSearchTest {
    @Test
    public void testHandleRequest404() throws IOException, URISyntaxException {
        Handler technical = new Handler("technical");
        URI unknown = new URI(null, null, "/404", "", null);
        assertEquals("Don't know how to handle that path!", technical.handleRequest(unknown));
    }

    @Test
    public void testHandleRequestHome() throws IOException, URISyntaxException {
        Handler technical = new Handler("technical");
        URI home = new URI(null, null, "/", "", null);
        assertEquals("There are 1391 files to search", technical.handleRequest(home));
    }

    @Test
    public void testHandleRequestSearch() throws IOException, URISyntaxException {
        Handler plos = new Handler("technical/plos"); 
        URI search = new URI(null, null, "/search", "q=base pair", null);
        assertEquals("There were 2 files found:\n[technical\\plos\\journal.pbio.0020190.txt, technical\\plos\\journal.pbio.0020223.txt]",
                        plos.handleRequest(search));
    }

    @Test
    public void testHandleRequestSearchNoQuery() throws IOException, URISyntaxException {
        Handler technical = new Handler("technical");
        URI search = new URI(null, null, "/search", "", null);
        assertEquals("To search, use a query such as '?q=cat'. Your query: ", technical.handleRequest(search));
    }

    @Test
    public void testHandleRequestSarchWrongQuery() throws IOException, URISyntaxException {
        Handler technical = new Handler("technical");
        URI search = new URI(null, null, "/search", "s=talk", null);
        assertEquals("To search, use a query such as '?q=cat'. Your query: s=talk", technical.handleRequest(search));
    }
}
