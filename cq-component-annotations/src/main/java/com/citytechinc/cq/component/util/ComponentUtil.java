package com.citytechinc.cq.component.util;

import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import javassist.CtField;
import javassist.CtMember;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class ComponentUtil {

    /**
     * Retrieves a field for a Class. To allow for the retrieval of inherited
     * fields, the class hierarchy is traversed upwards starting at the provided
     * class. If the top of the hierarchy is reached without finding a field of
     * the specified name, null is returned.
     *
     * @param clazz
     * @param fieldName
     * @return The Field specified by the provided name or null if no such field
     * could be found for the Class or its parents.
     */
    public static Field getField(Class<?> clazz, String fieldName) {
        Field retField = null;
        Class<?> curClass = clazz;

        while (retField == null && curClass != null) {
            try {
                retField = curClass.getDeclaredField(fieldName);
                curClass = curClass.getSuperclass();
            } catch (Exception e) {

            } finally {
                curClass = curClass.getSuperclass();
            }
        }

        return retField;
    }

    /**
     * Retrieves a Method for a Class.
     *
     * @param clazz
     * @param fieldName
     * @return The Method specified by the provided name or null if no such
     * Method could be found for the Class.
     */
    public static Method getMethod(Class<?> clazz, String methodName) {
        for (Method method : clazz.getMethods()) {
            if (method.getName().equals(methodName)) {
                return method;
            }
        }

        return null;
    }

    public static Class<?> getTypeForMember(CtMember ctMember, Class<?> containingClass)
        throws InvalidComponentFieldException {
        Class<?> type = null;
        Type genericType = null;
        if (ctMember instanceof CtField) {
            Field memberField = getField(containingClass, ctMember.getName());
            type = memberField.getType();
            genericType = memberField.getGenericType();
        } else {
            Method memberMethod = getMethod(containingClass, ctMember.getName());
            type = memberMethod.getReturnType();
            genericType = memberMethod.getGenericReturnType();
        }
        if (List.class.isAssignableFrom(type)) {
            ParameterizedType parameterizedType = (ParameterizedType) genericType;
            if (parameterizedType.getActualTypeArguments().length == 0
                || parameterizedType.getActualTypeArguments().length > 1) {
                throw new InvalidComponentFieldException(
                    "List dialog property found with a paramaterized type count not equal to 1");
            }
            type = (Class<?>) parameterizedType.getActualTypeArguments()[0];
        }
        return type;
    }
}
