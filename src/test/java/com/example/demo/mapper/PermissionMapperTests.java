package com.example.demo.mapper;

import com.example.demo.model.mapper.PermissionMapper;
import com.example.demo.security.Permission;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.example.demo.security.Permission.*;
import static org.junit.jupiter.api.Assertions.*;

public class PermissionMapperTests {
    @Test
    public void stringToEnumMapping() {
        String permissionString = "USER_CREATE";
        Permission permission = PermissionMapper.INSTANCE.toPermission(permissionString);
        assertEquals(permission, Permission.USER_CREATE);
    }

    @Test
    public void stringToEnumMappingException() {
        String permissionString = "error_string";
        Permission permission = PermissionMapper.INSTANCE.toPermission(permissionString);
        assertNull(permission);
    }

    @Test
    public void stringListToEnumListMapping() {
        List<String> strings = Arrays.asList("DICTIONARY_CREATE", "DICTIONARY_READ", "DICTIONARY_UPDATE", "DICTIONARY_DELETE");
        List<Permission> permissions = PermissionMapper.INSTANCE.toPermissionList(strings);
        List<Permission> permissionsCorrect = Arrays.asList(DICTIONARY_CREATE, DICTIONARY_READ, DICTIONARY_UPDATE, DICTIONARY_DELETE);
        assertArrayEquals(permissions.toArray(), permissionsCorrect.toArray());
    }
}
