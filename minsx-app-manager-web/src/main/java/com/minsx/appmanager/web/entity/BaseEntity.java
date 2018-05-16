/*
 *
 *  * Copyright © 2017-2018 minsx.com All rights reserved
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
package com.minsx.appmanager.web.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

@MappedSuperclass
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "createTime", "edit_time"})
public class BaseEntity {

    @Column(name = "create_time", updatable = false)
    private Date createTime;

    @Column(name = "edit_time")
    private Date editTime;

    protected Date getCreatedAt() {
        return createTime;
    }

    protected Date getUpdatedAt() {
        return editTime;
    }

    protected void setUpdatedAt(Date updatedAt) {
        this.editTime = updatedAt;
    }

    @PrePersist
    public void prePersist() {
        this.createTime = new Date();
        this.editTime = this.createTime;
    }

    @PreUpdate
    public void preUpdate() {
        this.editTime = new Date();
    }

}
