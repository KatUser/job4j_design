package ru.job4j.generic;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RoleStoreTest {

    @Test
    void whenAddAndFindThenRolenameIsAdmin() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Admin"));
        Role result = roleStore.findById("1");
        assertThat(result.getRolename()).isEqualTo("Admin");
    }

    @Test
    void whenAddAndFindThenRoleIsNull() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("2", "User"));
        Role result = roleStore.findById("11");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindRolenameIsUser() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "User"));
        roleStore.add(new Role("1", "Admin"));
        Role result = roleStore.findById("1");
        assertThat(result.getRolename()).isEqualTo("User");
    }

    @Test
    void whenReplaceThenRolenameIsAdmin() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "User"));
        roleStore.replace("1", new Role("1", "Admin"));
        Role result = roleStore.findById("1");
        assertThat(result.getRolename()).isEqualTo("Admin");
    }

    @Test
    void whenNoReplaceRoleThenNoChangeRolename() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Admin"));
        roleStore.replace("10", new Role("10", "User"));
        Role result = roleStore.findById("1");
        assertThat(result.getRolename()).isEqualTo("Admin");
    }

    @Test
    void whenDeleteUserThenUserIsNull() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Admin"));
        roleStore.delete("1");
        assertThat(roleStore.findById("1")).isNull();
    }

    @Test
    void whenNoDeleteRoleThenRolenameIsAdmin() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Admin"));
        roleStore.delete("2");
        assertThat(roleStore.findById("1").getRolename()).isEqualTo("Admin");
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Admin"));
        boolean result = roleStore.replace("1", new Role("1", "User"));
        assertThat(result).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "Admin"));
        boolean result = roleStore.replace("2", new Role("2", "User"));
        assertThat(result).isFalse();
    }
}