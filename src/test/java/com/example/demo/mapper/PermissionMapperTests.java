package com.example.demo.mapper;

import com.example.demo.model.mapper.PermissionMapper;
import com.example.demo.model.enums.Permission;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.example.demo.model.enums.Permission.*;
import static org.junit.jupiter.api.Assertions.*;

class PermissionMapperTests {
    @Test
    void stringToEnumMapping() {
        String permissionString = "USER_CREATE";
        Permission permission = PermissionMapper.INSTANCE.toPermission(permissionString);
        assertEquals(Permission.USER_CREATE, permission);
    }

    @Test
    void stringToEnumMappingException() {
        String permissionString = "error_string";
        Permission permission = PermissionMapper.INSTANCE.toPermission(permissionString);
        assertNull(permission);
    }

    @Test
    void stringListToEnumListMapping() {
        List<String> strings = Arrays.asList("DICTIONARY_CREATE", "DICTIONARY_READ", "DICTIONARY_UPDATE", "DICTIONARY_DELETE");
        List<Permission> permissions = PermissionMapper.INSTANCE.toPermissionList(strings);
        List<Permission> permissionsCorrect = Arrays.asList(DICTIONARY_CREATE, DICTIONARY_READ, DICTIONARY_UPDATE, DICTIONARY_DELETE);
        assertArrayEquals(permissionsCorrect.toArray(), permissions.toArray());
    }

    @Test
    void enumListToStringListMapping() {
        List<Permission> permissions = Arrays.asList(DICTIONARY_CREATE, DICTIONARY_READ, DICTIONARY_UPDATE, DICTIONARY_DELETE);
        List<String> stringsCorrect = Arrays.asList("DICTIONARY_CREATE", "DICTIONARY_READ", "DICTIONARY_UPDATE", "DICTIONARY_DELETE");
        List<String> strings = PermissionMapper.INSTANCE.toStringList(permissions);
        assertArrayEquals(stringsCorrect.toArray(), strings.toArray());
    }
}
