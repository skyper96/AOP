import java.io.*;
import org.apache.log4j.Logger;

import org.aspectj.lang.Signature;

aspect Trace {
    pointcut traceMethods():(execution(* *(..)));

    before():traceMethods(){
        Signature sig = thisJoinPointStaticPart.getSignature();
        String line = "" + thisJoinPointStaticPart.getSourceLocation().getLine();
        String sourceName = thisJoinPointStaticPart.getSourceLocation().getWithinType().getCanonicalName();

        Logger log = Logger.getLogger(sourceName);
        System.out.println("SSS");
        log.info("Call from "
                + sourceName
                + " line " +
                line
                + " to " + sig.getDeclaringTypeName() + "." + sig.getName());
    }
}