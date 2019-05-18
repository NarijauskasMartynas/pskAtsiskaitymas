package lt.vu.usecases.async;

import lt.vu.usecases.cdi.RescueOrAsync;
import org.apache.deltaspike.core.api.future.Futureable;

import javax.ejb.AsyncResult;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.concurrent.Future;

@ApplicationScoped
public class AsyncComponent implements AsyncInterface, Serializable {


    @Futureable
    // be šios anotacijos negalėsime gauti @RescueOrAsync EntityManager
    public Future<String> asyncMethod() {
        System.out.println("Async component starts working");
        try {
            Thread.sleep(10000); // sleep for 10 seconds
        } catch (InterruptedException e) {
        }
        System.out.println("Big task completed");
        return new AsyncResult<>("My result woah:)");
    }

}

