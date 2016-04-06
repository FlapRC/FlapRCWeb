import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

@RequestScoped
@ManagedBean
public class InjecaoHello {
	
	@Inject
	private Hello hello;

	public Hello getHello() {
		return hello;
	}

}