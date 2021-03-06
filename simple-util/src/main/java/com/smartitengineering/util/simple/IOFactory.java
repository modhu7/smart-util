/*
 * This is a utility project for wide range of applications
 * 
 * Copyright (C) 8  Imran M Yousuf (imyousuf@smartitengineering.com)
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  10-1  USA
 */
package com.smartitengineering.util.simple;

import com.smartitengineering.util.simple.io.ContentBuffer;
import com.smartitengineering.util.simple.io.StringBufferInputStream;
import com.smartitengineering.util.simple.io.StringInputStream;
import com.smartitengineering.util.simple.reflection.ClassScanner;
import com.smartitengineering.util.simple.reflection.DefaultClassScannerImpl;
import java.io.InputStream;
import java.lang.annotation.Annotation;

/**
 *
 * @author imyousuf
 * @since 0.1.1
 */
public final class IOFactory {

    private IOFactory() {
        throw new AssertionError();
    }

    public static InputStream getStringInputStream(final String string) {
        if (string == null) {
            throw new IllegalArgumentException();
        }
        return new StringInputStream(string);
    }

    public static <T extends InputStream & ContentBuffer> T getStringBufferingIOStream(
        final InputStream bufferedStream) {
        return (T) new StringBufferInputStream(bufferedStream);
    }

    public static ClassScanner getDefaultClassScanner() {
        return new DefaultClassScannerImpl();
    }

    public static String getAnnotationNameForVisitor(
        Class<? extends Annotation> annotationClass) {
        if (annotationClass == null) {
            throw new IllegalArgumentException();
        }
        return "L".concat(annotationClass.getName().replaceAll("\\.", "/")).
            concat(";");
    }

    public static Class getAnnotationClassFromVisitorName(
        String name)
        throws ClassNotFoundException,
               IndexOutOfBoundsException {
        if (name == null) {
            throw new IllegalArgumentException();
        }
        return Class.forName(name.substring(1, name.length() - 1).replaceAll(
            "/", "."));
    }

    public static Class getClassFromVisitorName(String name)
        throws ClassNotFoundException,
               IndexOutOfBoundsException {
        if (name == null) {
            throw new IllegalArgumentException();
        }
        return Class.forName(name.replaceAll(
            "/", "."));
    }

    public static String getClassNameForVisitor(
        Class clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException();
        }
        return clazz.getName().replaceAll("\\.", "/");
    }
}
