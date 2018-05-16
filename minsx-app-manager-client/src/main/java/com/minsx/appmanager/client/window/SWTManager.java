/*
 *
 *  * Copyright 2018 https://www.minsx.com
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */
package com.minsx.appmanager.client.window;

import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.SWTResourceManager;

import java.io.IOException;

public class SWTManager extends SWTResourceManager {

    private final static String IMAGE_PACKAGE_PATH = "/org/eclipse/wb/swt/";
    private final static String IMAGE_RESOURCE_PATH = "image";

    public static Image getPackageImage(String imageName) {
        return getImage(SWTResourceManager.class, IMAGE_PACKAGE_PATH + imageName);
    }

    public static Image getResourceImage(String imageName) {
        Image image = null;
        try {
            image = getImage(SWTResourceManager.class.getClassLoader().getResourceAsStream(IMAGE_RESOURCE_PATH + "/" + imageName));
        } catch (IOException e) {
            throw new RuntimeException(String.format("could not found resource image [%s]", imageName), e);
        }
        return image;
    }


}
