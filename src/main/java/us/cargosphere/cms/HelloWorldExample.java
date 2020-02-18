package us.cargosphere.cms;

import java.util.HashMap;
import java.util.Map;

//import org.neo4j.driver.v1.*;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;

//  import org.neo4j.driver.v1.TransactionWork;

/**
 * Hello world!
 *
 */
public class HelloWorldExample implements AutoCloseable
{
    private  Driver driver;

    public HelloWorldExample( String uri, String user, String password )
    {
        driver = GraphDatabase.driver( uri, AuthTokens.basic( user, password ) );
    }

    @Override
    public void close() throws Exception
    {
        driver.close();
    }

    public void printGreeting( final String message )
    {
        

        try ( Session session = driver.session() )
        {
            String greeting = session.writeTransaction( new TransactionWork<String>()
            {
                

                @Override
                public String execute( Transaction tx )
                {
                    Map<String, Object> params= new HashMap<>();
                    params.put("message", message);
                    StatementResult result = tx.run( "CREATE (a:Greeting) " +
                                                     "SET a.message = $message " +
                                                     "RETURN a.message + ', from node ' + id(a)",
                            params );
                    return result.single().get( 0 ).asString();
                }
            } );
            System.out.println( greeting );
        }
    }

    public static void main( String[] args )
    {
        System.out.println( "Hello!" );
    }
}
