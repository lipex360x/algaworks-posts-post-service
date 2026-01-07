<div align="center">

## <a name="header">AlgaPosts - Post Service</a>

</div>

Post Service is responsible for managing posts (create, list, and retrieve). It exposes a REST API and returns both **Post Summary** and **Post Details** representations.

---

* API Port: `8080`

#### Create Post

```http
POST /api/posts
Content-Type: application/json

{
  "title": "title",
  "body": "post content",
  "author": "author name"
}
```

**HTTP Status Code**
- `201 Created` - Post Summary Output
- `400 Bad Request` - Invalid Request

**Response:** - Post Summary Output
```json
{
  "id": "UUID",
  "author": "author name",
  "title": "post title",
  "summary": "summarised post body"
}
```

#### List All Posts

```http
GET /algaposts/api/posts
```
* Query Params
    * page: number
    * size: number
    * sort: asc/desc
        * id
        * author
        * title
        * summary / body

**HTTP Status Code**
- `200 Ok` - Paginated Response Body

**Response:**
```json
{
  "page": 0,
  "size": 10,
  "totalElements": 45,
  "totalPages": 5,
  "content": [ /* list of post summary output */ ]
}
```


#### Find One Post
```http
GET /algaposts/api/posts/{id}
```

**HTTP Status Code**
- `200 OK` - Post Output
- `404 Not Found` - Post not found

**Response:** - Post Output
```json
{
  "id": "UUID",
  "author": "author name",
  "title": "post title",
  "body": "completed post body",
  "wordCount": 200,
  "calculatedValue": 20.00
}
```

---