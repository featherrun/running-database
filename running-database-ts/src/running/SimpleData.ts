/**
 * Created by featherrun on 2017/7/28.
 */
namespace running {
    export class SimpleData<T> {
        private data:T[];
        private primaryKey:string;
        private dataMap:any;
        private groupMap:any;

        constructor(primaryKey:string) {
            this.primaryKey = primaryKey;
        }

        setData(data:T[]):void {
            this.data = data;
        }

        /**
         * 根据主键获取唯一数据
         * @param key
         * @returns {any}
         */
        getItem(key:any):T {
            if (this.dataMap == null) {
                this.dataMap = {};
                for (let t of this.data) {
                    this.dataMap[ t[this.primaryKey] ] = t;
                }
            }
            return this.dataMap[key];
        }

        /**
         * 获取所有数据
         * @returns {Array}
         */
        getList():T[] {
            return this.data;
        }

        /**
         * 获取分组数据
         * @param keyName
         * @param key
         * @returns {Array}
         */
        getGroup(keyName:string, key:any):T[] {
            if (this.groupMap == null) {
                this.groupMap = {};
            }
            let map = this.groupMap[keyName];
            if (map == null) {
                map = {};
                for (let t of this.data) {
                    let v = t[keyName];
                    if (map[v] == null) {
                        map[v] = [t];
                    } else {
                        map[v].push(t);
                    }
                }
                this.groupMap[keyName] = map;
            }
            return map[key];
        }
    }
}