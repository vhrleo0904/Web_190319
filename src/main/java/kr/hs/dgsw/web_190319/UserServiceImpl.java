package kr.hs.dgsw.web_190319;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    List<User> userList;

    public UserServiceImpl() {
        this.userList = new ArrayList<>();
        this.userList.add(new User("1111", "user1", "user111@dgsw"));
        this.userList.add(new User("2222", "user2", "user222@dgsw"));
        this.userList.add(new User("3333", "user3", "user333@dgsw"));
    }

    @Override
    public List<User> list() {
        return this.userList;
    }

    @Override
    public User view(String id) {
        User found = this.userList.stream()
                .filter(user -> user.getId().equals(id))
                .findAny()
                .orElse(null);
        return found;
    }

    @Override
    public boolean add(User user) {
        User found = this.view(user.getId());
        if(found != null)
            return false;
        return this.userList.add(user);
    }

    @Override
    public User update(User user) {
        User found = this.view(user.getName());
        if(found != null)
            found.setEmail(user.getEmail());
        return found;
    }

    @Override
    public boolean delete(String id) {
        User found = this.view(id);
        return this.userList.remove(found);
    }

    public User find1(String id) {
        Iterator<User> iterator = this.userList.iterator();
        while(iterator.hasNext()) {
            User found = iterator.next();
            if(found.getName().equals(id))
                return found;
        }
        return null;
    }

    public User find2(String id) {        // Java 5 이상
        for (User found: this.userList) {
            if (found.getName().equals(id))
                return found;
        }
        return null;
    }

    public User find3(String id) {        // Java 8 이상 : 지금 view 메소드 내용
        return null;
    }
}
