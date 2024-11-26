package structural.decorator;

public abstract class Decorator extends Component {
	protected Component component; //자식 구현체에서 이걸 써야 함 
	
	public Decorator(Component component) {
		this.component = component;
	}

}
