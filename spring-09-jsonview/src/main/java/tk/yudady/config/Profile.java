package tk.yudady.config;

public class Profile {
	public interface PublicView {}
	public interface FriendsView extends PublicView{}
	public interface FamilyView extends FriendsView {}
}
